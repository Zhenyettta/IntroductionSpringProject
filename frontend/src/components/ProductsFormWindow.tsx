import {Alert, Button, Form} from "react-bootstrap";
import {Typeahead} from "react-bootstrap-typeahead";
import {TfiClose} from "react-icons/tfi";
import React, {MouseEventHandler, useEffect, useState} from "react";
import "../styles/form.css";
import {Category, Product} from "../utils/types.ts";
import {CATEGORIES_ENDPOINT} from "../utils/apiEndpoints.ts";
import {AUTHORIZATION_KEY, ROLE_KEY, USER_ROLE_EMPLOYEE, USER_ROLE_MANAGER} from "../utils/constants.ts";
import {handleLogout} from "../utils/sharedFunctions.ts";
import axios from "axios";

interface Props {
    title: string
    isDataCorrect: boolean
    onSubmit: (product: Product) => void
    handleCloseButtonClick: MouseEventHandler<HTMLButtonElement>
    startingProduct?: Product
}

export default function ProductsFormWindow({
                                               title,
                                               onSubmit,
                                               handleCloseButtonClick,
                                               startingProduct,
                                           }: Props) {
    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<Category[]>([]);
    const [isDataCorrect, setIsDataCorrect] = useState(true);

    useEffect(getCategories, []);
    useEffect(() => setSelectedCategory([startingProduct?.category ?? {
        id: 0,
        name: "",
        description: ""
    }]), [startingProduct]);

    function getCategories() {
        axios.get(CATEGORIES_ENDPOINT, {
            headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}
        })
            .then(response => {
                setCategories(response.data as Category[]);
            })
            .catch(() => {
                handleLogout();
                setCategories([]);
            });
    }

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (selectedCategory.length === 0) {
            setIsDataCorrect(false);
            console.log(isDataCorrect);
            return;
        }

        let submittedProduct: Product;
        let updatedInStockAmount = startingProduct?.inStockAmount ?? 0;

        if (startingProduct) {
            updatedInStockAmount = parseInt(event.target.inStockAmount.value) - startingProduct.inStockAmount;
        }

        if (localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER) {
            submittedProduct = {
                id: startingProduct?.id ?? 0,
                name: event.target.name.value as string,
                description: event.target.description.value as string,
                producer: event.target.producer.value as string,
                inStockAmount: parseInt(event.target.inStockAmount.value),
                price: parseFloat(event.target.price.value),
                category: selectedCategory[0]
            };
        } else {
            submittedProduct = {
                id: startingProduct?.id ?? 0,
                name: event.target.name.value as string,
                description: event.target.description.value as string,
                producer: event.target.producer.value as string,
                inStockAmount: updatedInStockAmount,
                price: parseFloat(event.target.price.value),
                category: selectedCategory[0]
            };
        }
        console.log(updatedInStockAmount);

        onSubmit(submittedProduct);
    }

    if(localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER) {
        return (

            <div className="form-background">
                <div className="form-main" style={{height: "90%"}}>
                    <button className="close-icon" onClick={handleCloseButtonClick}>
                        <TfiClose/>
                    </button>
                    <div className="flex justify-center items-center mt-6 mb-8">
                        <p className="text-2xl font-bold">{title}</p>
                    </div>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-4" controlId="name">
                            <Form.Label>Product name</Form.Label>
                            <Form.Control required={true} type="text" name="name" placeholder="Product name..."
                                          autoComplete="off" defaultValue={startingProduct?.name ?? ""}/>
                            <Form.Control.Feedback type="invalid">Please enter a product name</Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="description">
                            <Form.Label>Product description</Form.Label>
                            <Form.Control type="text" name="description" placeholder="Product description..."
                                          autoComplete="off" defaultValue={startingProduct?.description ?? ""}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="category">
                            <Form.Label>Product category</Form.Label>
                            <Typeahead
                                id="category"
                                onChange={setSelectedCategory}
                                options={categories}
                                placeholder="Choose category..."
                                selected={selectedCategory}
                                labelKey="name"
                                allowNew={false}
                            />
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="producer">
                            <Form.Label>Product producer</Form.Label>
                            <Form.Control required={true} type="text" name="producer" placeholder="Product producer..."
                                          defaultValue={startingProduct?.producer ?? ""}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="inStockAmount">
                            <Form.Label>Product in stock amount</Form.Label>
                            <Form.Control required={true} type="number" name="inStockAmount"
                                          placeholder="Product in stock amount..."
                                          autoComplete="off" defaultValue={startingProduct?.inStockAmount ?? "" } min={0}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="price">
                            <Form.Label>Product price</Form.Label>
                            <Form.Control required={true} type="text" name="price" placeholder="Product price..."
                                          autoComplete="off" defaultValue={startingProduct?.price ?? ""} pattern="[0-9]+(\.[0-9]+)?"/>
                        </Form.Group>
                        {!isDataCorrect && (<Alert variant="danger">Incorrect data!</Alert>)}
                        <Button variant="primary" type="submit"
                                className="w-full px-5 py-2 mt-3 bg-our-green font-semibold text-white">
                            Submit
                        </Button>
                    </Form>
                </div>
            </div>
        );
    }
    if (localStorage.getItem(ROLE_KEY) === USER_ROLE_EMPLOYEE) {
        return (

            <div className="form-background">
                <div className="form-main" style={{height: "90%"}}>
                    <button className="close-icon" onClick={handleCloseButtonClick}>
                        <TfiClose/>
                    </button>
                    <div className="flex justify-center items-center mt-6 mb-8">
                        <p className="text-2xl font-bold">{title}</p>
                    </div>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-4" controlId="name">
                            <Form.Label>Product name</Form.Label>
                            <Form.Control required={true} type="text" name="name" placeholder="Product name..."
                                          autoComplete="off" defaultValue={startingProduct?.name ?? ""} disabled={true}/>
                            <Form.Control.Feedback type="invalid">Please enter a product name</Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="description">
                            <Form.Label>Product description</Form.Label>
                            <Form.Control type="text" name="description" placeholder="Product description..."
                                          autoComplete="off" defaultValue={startingProduct?.description ?? "" } disabled={true}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="category">
                            <Form.Label>Product category</Form.Label>
                            <Typeahead
                                id="category"
                                options={categories}
                                placeholder="Choose a category..."
                                labelKey="name"
                                allowNew={false}
                                disabled={true}
                            />
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="producer">
                            <Form.Label>Product producer</Form.Label>
                            <Form.Control required={true} type="text" name="producer" placeholder="Product producer..."
                                          defaultValue={startingProduct?.producer ?? ""} disabled={true}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="inStockAmount">
                            <Form.Label>Product in stock amount</Form.Label>
                            <Form.Control required={true} type="number" name="inStockAmount"
                                          placeholder="Product in stock amount..."
                                          autoComplete="off" defaultValue={startingProduct?.inStockAmount ?? ""} min={0}/>
                        </Form.Group>
                        <Form.Group className="mb-4" controlId="price">
                            <Form.Label>Product price</Form.Label>
                            <Form.Control required={true} type="text" name="price" placeholder="Product price..."
                                          autoComplete="off" defaultValue={startingProduct?.price ?? ""} disabled={true} pattern="[0-9]+(\.[0-9]+)?"/>
                        </Form.Group>
                        {!isDataCorrect && (<Alert variant="danger">Incorrect data!</Alert>)}
                        <Button variant="primary" type="submit"
                                className="w-full px-5 py-2 mt-3 bg-our-green font-semibold text-white">
                            Submit
                        </Button>
                    </Form>
                </div>
            </div>
        );
    }
}