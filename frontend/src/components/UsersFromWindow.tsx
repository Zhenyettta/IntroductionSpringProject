import {Alert, Button, Form} from "react-bootstrap";
import {Typeahead} from "react-bootstrap-typeahead";
import {TfiClose} from "react-icons/tfi";
import React, {MouseEventHandler, useState} from "react";
import "../styles/form.css";
import {User} from "../utils/types.ts";

interface Props {
    title: string
    isDataCorrect: boolean
    onSubmit: (user: User) => void
    handleCloseButtonClick: MouseEventHandler<HTMLButtonElement>
    startingUser?: User
}

export default function UsersFormWindow({
                                               title,
                                               onSubmit,
                                               handleCloseButtonClick,
                                               startingUser,
                                           }: Props) {
    const [isDataCorrect, setIsDataCorrect] = useState(true);
    const [selectedRole, setSelectedRole] = useState('');
    const [id, setId] = useState(startingUser?.username ?? '');



    const handleRoleChange = (selectedOptions) => {
        if (selectedOptions.length > 0) {
            setSelectedRole(selectedOptions[0]);
        } else {
            setSelectedRole('');
        }
    };
    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const submittedProduct: User = {
            username: event.target.username.value as string,
            role: selectedRole,
            password: event.target.password?.value ?? "" as string,
        };
        setId(submittedProduct.username);
        onSubmit(submittedProduct);
    }

    return (
        <div className="form-background">
            <div className="form-main" style={{height: "55%"}}>
                <button className="close-icon" onClick={handleCloseButtonClick}>
                    <TfiClose/>
                </button>
                <div className="flex justify-center items-center mt-6 mb-8">
                    <p className="text-2xl font-bold">{title}</p>
                </div>
                <Form onSubmit={handleSubmit}>
                    <input type="hidden" name="id" value={id} />
                    <Form.Group className="mb-4" controlId="username">
                        <Form.Label>Username</Form.Label>
                        <Form.Control
                            required={true}
                            type="text"
                            name="name"
                            placeholder="User username.."
                            autoComplete="off"
                            defaultValue={startingUser?.username ?? ""}
                        />
                        <Form.Control.Feedback type="invalid">Please enter a username</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-4" controlId="role">
                        <Form.Label>User role</Form.Label>
                        <Typeahead
                            id="role"
                            placeholder="User role..."
                            options={['MANAGER', 'EMPLOYEE']}
                            onChange={handleRoleChange}
                            selected={[selectedRole]}
                        />
                        <Form.Control.Feedback type="invalid">
                            Please enter a user role
                        </Form.Control.Feedback>
                    </Form.Group>
                    {!startingUser && (
                    <Form.Group className="mb-4" controlId="password">
                        <Form.Label>User password</Form.Label>
                        <Form.Control type="password" name="description" placeholder="User password..."
                                      autoComplete="off" defaultValue={startingUser?.password ?? ""}/>
                    </Form.Group>)}


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