import {Button, Col, Container, Row} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import axios, {AxiosError} from "axios";

import SortingButton from "../components/SortingButton.tsx";
import CardGrid from "../components/CardGrid.tsx";
import Title from "../components/Title.tsx";
import {Category} from "../utils/types.ts";
import {AUTHORIZATION_KEY, OPERATION_CREATE, OPERATION_EDIT, ROLE_KEY, USER_ROLE_MANAGER} from "../utils/constants.ts";
import CategoriesFormWindow from "../components/CategoriesFormWindow.tsx";
import {handleLogout} from "../utils/sharedFunctions.ts";
import {CATEGORIES_ENDPOINT} from "../utils/apiEndpoints.ts";

export default function Categories() {
    const [ascending, setAscending] = useState(true);
    const [categories, setCategories] = useState<Category[]>([]);
    const [isDataCorrect, setIsDataCorrect] = useState(true);
    const [isFormShown, setIsFormShown] = useState(false);
    const [formOperation, setFormOperation] = useState("");
    const [editingCategoryId, setEditingCategoryId] = useState<number>();
    const [startingCategoryName, setStartingCategoryName] = useState("");
    const [startingCategoryDescription, setStartingCategoryDescription] = useState("");

    useEffect(getCategories, []);

    function getCategories() {
        axios.get(CATEGORIES_ENDPOINT, {
            headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}
        })
            .then(response => {
                setIsDataCorrect(true);

                const receivedCategories = response.data as Category[];
                const sortedCategories = receivedCategories.sort(categoriesComparator);

                setCategories(ascending ? sortedCategories : sortedCategories.reverse());
            })
            .catch(() => {
                setIsDataCorrect(true);
                handleLogout();
            });
    }

    function handleSortClick() {
        setAscending(!ascending);
        setCategories([...categories].reverse());
    }

    function handleCreateButtonClick() {
        setFormOperation(OPERATION_CREATE);
        setIsFormShown(true);
    }

    function handleEditButtonClick(id: number) {
        const category = categories.find(c => c.id === id) as Category;
        setEditingCategoryId(id);
        setStartingCategoryName(category.name);
        setStartingCategoryDescription(category.description);

        setFormOperation(OPERATION_EDIT);
        setIsFormShown(true);
    }

    function closeForm() {
        setIsFormShown(false);
        setEditingCategoryId(undefined);
        setStartingCategoryName("");
        setStartingCategoryDescription("");
        setIsDataCorrect(true);
    }

    function handleSubmitForm(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const categoryName = event.target.name.value as string;
        const categoryDescription = event.target.description?.value as string;

        if (formOperation === OPERATION_CREATE) {
            handleCreate(categoryName, categoryDescription);
        } else if (formOperation === OPERATION_EDIT) {
            handleEdit(editingCategoryId as number, categoryName, categoryDescription);
        }
    }

    function handleCreate(categoryName: string, categoryDescription: string) {
        axios.post(CATEGORIES_ENDPOINT,
            {name: categoryName, description: categoryDescription},
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(response => {
                const receivedId = response.data as number;
                const category: Category = {id: receivedId, name: categoryName, description: categoryDescription};
                const allCategories = [...categories, category];
                const sortedCategories = allCategories.sort(categoriesComparator);

                setCategories(ascending ? sortedCategories : sortedCategories.reverse());

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }

                const responseCode = error.response.status;
                if (responseCode == 409) {
                    setIsDataCorrect(false);
                }
            });
    }

    function handleEdit(id: number, categoryName: string, categoryDescription: string) {
        axios.put(`${CATEGORIES_ENDPOINT}/${id}`,
            {name: categoryName, description: categoryDescription},
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(() => {
                const updatedCategories = [...categories];
                const editedCategoryIndex = categories.findIndex(c => c.id === id);
                updatedCategories[editedCategoryIndex] = {id: id, name: categoryName, description: categoryDescription};
                setCategories(updatedCategories);

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }

                const responseCode = error.response.status;
                if (responseCode == 409) {
                    setIsDataCorrect(false);
                }
            });
    }

    function handleDelete(id: number) {
        axios.delete(`${CATEGORIES_ENDPOINT}/${id}`,
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(() => {
                const updatedCategories = [...categories];
                const deletedCategoryIndex = categories.findIndex(c => c.id === id);
                updatedCategories.splice(deletedCategoryIndex, 1);

                setCategories(updatedCategories);
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }
            });
    }

    function categoriesComparator(category1: Category, category2: Category) {
        return category1.name.localeCompare(category2.name);
    }

    return (
        <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8" id="categories-container">
            <Title className="mt-7">Categories</Title>
            <Container className="my-5">
                <Row className="align-items-center">
                    <Col>
                        {localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER &&
                            <Button variant="primary"
                                    className="px-5 py-2 bg-our-green font-semibold text-white"
                                    onClick={handleCreateButtonClick}>
                                Add Category
                            </Button>
                        }
                    </Col>
                    <Col>
                        <div>
                            Ordered by: name
                            {" "}
                            <SortingButton ascending={ascending} onClick={handleSortClick}
                                           className="m-2 bg-our-green text-white"/>
                        </div>
                    </Col>
                </Row>
            </Container>

            <div>
                <CardGrid
                    categories={categories}
                    onEditButtonClick={handleEditButtonClick}
                    onDeleteButtonClick={handleDelete}/>
            </div>

            {isFormShown &&
                <CategoriesFormWindow
                    title={`${formOperation} category`}
                    handleSubmit={handleSubmitForm}
                    isDataCorrect={isDataCorrect}
                    handleCloseButtonClick={closeForm}
                    startingName={startingCategoryName}
                    startingDescription={startingCategoryDescription}
                />
            }
        </div>
    );
}