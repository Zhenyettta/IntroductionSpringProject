import {useState} from "react";
import {Alert, Button, Form} from "react-bootstrap";
import Trident from "../assets/trident.svg";
import "../styles/form.css";
import axios from "axios";
import {LOGIN_ENDPOINT} from "../utils/apiEndpoints.ts";
import {handleLogin} from "../utils/sharedFunctions.ts";

export default function Login() {
    const [areCredentialsWrong, setAreCredentialsWrong] = useState<boolean>(false);

    const wrongCredentialsAlert = areCredentialsWrong && (<Alert variant="danger">Wrong credentials!</Alert>);

    function handleChange() {
        setAreCredentialsWrong(false);
    }

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        const username = event.target.username.value as string;
        const password = event.target.password.value as string;

        axios.post(LOGIN_ENDPOINT, {username: username, password: password})
            .then(response => {
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
            <div className="form-main">
                <div className="flex justify-center items-center">
                    <img src={Trident} alt="Ukrainian Trident"/>
                </div>
                <div className="flex justify-center items-center my-4">
                    <p className="text-2xl font-bold">Sign in</p>
                </div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-4" controlId="formUsername">
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" name="username" placeholder="Enter your username..."
                                      onChange={handleChange}/>
                    </Form.Group>
                    <Form.Group className="mb-4" controlId="formPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" name="password" placeholder="Enter your password..."
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