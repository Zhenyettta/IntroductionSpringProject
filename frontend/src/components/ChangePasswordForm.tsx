import React, {useState} from "react";
import "../styles/form.css";
import Trident from "../assets/trident.svg";
import axios from "axios";
import {SELF_ENDPOINT} from "../utils/apiEndpoints.ts";
import {Alert, Button, Form} from "react-bootstrap";
import {AUTHORIZATION_KEY} from "../utils/constants.ts";
import {handleLogin} from "../utils/sharedFunctions.ts";
import {TfiClose} from "react-icons/tfi";

interface Props {
    handleClose: () => void
}

export default function ChangePasswordForm({handleClose}: Props) {
    const [areCredentialsWrong, setAreCredentialsWrong] = useState<boolean>(false);

    const wrongCredentialsAlert = areCredentialsWrong && (<Alert variant="danger">Wrong credentials!</Alert>);

    function handleChange() {
        setAreCredentialsWrong(false);
    }

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const oldPassword = event.target.oldPassword.value as string;
        const newPassword = event.target.newPassword.value as string;
        const confirmPassword = event.target.confirmPassword.value as string;

        if (newPassword !== confirmPassword) {
            setAreCredentialsWrong(true);
            return;
        }

        axios.put(SELF_ENDPOINT,
            {
                oldPassword: oldPassword,
                newPassword: newPassword
            },
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then((response) => {
                console.log("inside")
                setAreCredentialsWrong(false);

                const token = response.data;
                handleLogin(token);
            })
            .catch(() => {
                setAreCredentialsWrong(true);
            });
    }


    return (
        <div className="form-background">
            <div className="form-main" style={{height: "40em"}}>
                <div className="flex justify-center items-center">
                    <img src={Trident} alt="Ukrainian Trident"/>
                </div>
                <button className="close-icon" onClick={handleClose}>
                    <TfiClose/>
                </button>
                <div className="flex justify-center items-center my-4">
                    <p className="text-2xl font-bold">Change password</p>
                </div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-4" controlId="formPassword">
                        <Form.Label>Old password</Form.Label>
                        <Form.Control type="password" name="oldPassword" placeholder="Enter your old password..."
                                      onChange={handleChange}/>

                    </Form.Group>
                    <Form.Group className="mb-4" controlId="formPassword">
                        <Form.Label>New password</Form.Label>
                        <Form.Control type="password" name="newPassword" placeholder="Enter your new password..."
                                      onChange={handleChange}/>

                    </Form.Group>
                    <Form.Group className="mb-4" controlId="formPassword">
                        <Form.Label>Confirm new password</Form.Label>
                        <Form.Control type="password" name="confirmPassword" placeholder="Confirm your new password..."
                                      onChange={handleChange}/>

                    </Form.Group>
                    {wrongCredentialsAlert}
                    <Button variant="primary" type="submit"
                            className="w-full px-5 py-2 mt-3 bg-our-green font-semibold text-white">
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    );
}
