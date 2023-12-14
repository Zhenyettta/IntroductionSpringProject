import Title from "../components/Title.tsx";
import {Button, Col, Container, Row} from "react-bootstrap";
import {useEffect, useState} from "react";
import {User} from "../utils/types.ts";
import axios, {AxiosError} from "axios";
import {USERS_ENDPOINT} from "../utils/apiEndpoints.ts";
import {AUTHORIZATION_KEY, OPERATION_CREATE, OPERATION_EDIT, USERNAME_KEY} from "../utils/constants.ts";
import {handleLogout} from "../utils/sharedFunctions.ts";
import UsersFromWindow from "../components/UsersFromWindow.tsx";
import UserTable from "../components/UserTable.tsx";


export default function Users() {
    const [users, setUsers] = useState<User[]>([]);
    const [isDataCorrect, setIsDataCorrect] = useState(true);
    const [isFormShown, setIsFormShown] = useState(false);
    const [formOperation, setFormOperation] = useState("");
    const [editingUser, setEditingUser] = useState<User>();

    useEffect(getUsers, []);

    function getUsers() {
        axios.get(USERS_ENDPOINT, {
            headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}
        })
            .then(response => {
                setIsDataCorrect(true);
                setUsers(response.data as User[]);
            })
            .catch(() => {
                setIsDataCorrect(true);
                handleLogout();
            });
    }

    function handleCreateButtonClick() {
        setFormOperation(OPERATION_CREATE);
        setIsFormShown(true);
    }

    function handleEditButtonClick(id: string) {
        const username = localStorage.getItem(USERNAME_KEY);
        if (id === username) {
            alert("You can't edit yourself.");
            return;
        }
        const user = users.find(p => p.username === id) as User;
        setEditingUser(user);

        setFormOperation(OPERATION_EDIT);
        setIsFormShown(true);
    }

    function closeForm() {
        setIsFormShown(false);
        setEditingUser(undefined);
        setIsDataCorrect(true);
    }

    function handleSubmitForm(user: User) {
        if (formOperation === OPERATION_CREATE) {
            handleCreate(user);
        } else if (formOperation === OPERATION_EDIT) {
            handleEdit(user);
        }
    }

    function handleCreate(user: User) {
        axios.post(USERS_ENDPOINT,
            {
                username: user.username,
                role: user.role,
                password: user.password,
            },
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(response => {
                user.username = response.data as string;

                setUsers([...users, user]);

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

    function handleEdit(user: User) {
        axios.put(
            `${USERS_ENDPOINT}/${user.username}`,
            {role: user.role},
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}}
        )
            .then(() => {
                const updatedUsers = [...users];
                const editedUserIndex = users.findIndex(p => p.username === user.username);
                updatedUsers[editedUserIndex].role = user.role;
                setUsers(updatedUsers);

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }

                const responseCode = error.response.status;

                if (responseCode === 409) {
                    setIsDataCorrect(false);
                }
            });
    }

    function handleDelete(id: string) {
        const username = localStorage.getItem(USERNAME_KEY);

        if (id === username) {
            alert("You can't delete yourself.");
            return;
        }

        axios.delete(`${USERS_ENDPOINT}/${id}`, {
            headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)},
        })
            .then(() => {
                const updatedUsers = [...users];
                const deletedUserIndex = users.findIndex((p) => p.username === id);
                updatedUsers.splice(deletedUserIndex, 1);

                setUsers(updatedUsers);
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }
            });
    }


    return (
        <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8" id="goods-container">
            <Title className="mt-7">Users</Title>
            <Container className="my-5">
                <Row className="align-items-center">
                    <Col>
                        <Button variant="primary"
                                className="px-5 py-2 bg-our-green font-semibold text-white"
                                onClick={handleCreateButtonClick}>
                            Add user
                        </Button>
                    </Col>
                </Row>
            </Container>
            <div className="mt-8 mb-8">
                <UserTable
                    users={users}
                    onEditButtonClick={handleEditButtonClick}
                    onDeleteButtonClick={handleDelete}
                />
            </div>
            {isFormShown &&
                <UsersFromWindow
                    title={`${formOperation} user`}
                    isDataCorrect={isDataCorrect}
                    onSubmit={handleSubmitForm}
                    handleCloseButtonClick={closeForm}
                    startingUser={editingUser}
                />
            }
        </div>
    );
}