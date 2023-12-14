import {BrowserRouter, Navigate, Route, Routes} from 'react-router-dom';
import NavigationBar from './components/NavigationBar.tsx';
import Categories from './pages/Categories.tsx';
import Products from './pages/Products.tsx';
import Users from './pages/Users.tsx';
import Login from "./pages/Login.tsx";
import HomePage from "./pages/HomePage.tsx";
import {AUTHORIZATION_KEY, EXPIRES_AT_KEY, ROLE_KEY, USER_ROLE_MANAGER} from "./utils/constants.ts";
import {useEffect, useState} from "react";
import {SELF_ENDPOINT} from "./utils/apiEndpoints.ts";
import axios from "axios";
import {Button, Modal} from "react-bootstrap";

export default function App() {
    const tokenExpiresAt = parseInt(localStorage?.getItem(EXPIRES_AT_KEY) ?? "0", 10) * 1000;
    const isTokenValid = tokenExpiresAt > Date.now();
    const userRole = localStorage.getItem(ROLE_KEY);

    const [showDialog, setShowDialog] = useState(false);
    const [isFormShown, setIsFormShown] = useState(false);

    const handleClose = () => setShowDialog(false);
    const handleShow = () => setShowDialog(true);

    useEffect(checkIfPasswordIsDefault, [isTokenValid]);

    function checkIfPasswordIsDefault() {
        if (!isTokenValid)
            return;

        axios.get(SELF_ENDPOINT, {
            headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}
        })
            .then(response => {
                const isPasswordDefault = response.data.isPasswordDefault;
                if (isPasswordDefault) {
                    handleShow();
                }
            })
            .catch();
    }

    return (
        <div className="bg-gradient-to-r from-primary to-secondary min-h-screen">
            <BrowserRouter>
                <NavigationBar isFormShown={isFormShown} setIsFormShown={setIsFormShown}/>
                <Routes>
                    <Route path="/" element={
                        isTokenValid
                            ? <HomePage/>
                            : <Navigate to="/login"/>
                    }/>
                    <Route path="/login" element={
                        isTokenValid
                            ? <Navigate to="/categories"/>
                            : <Login/>
                    }/>
                    <Route path="/categories" element={
                        isTokenValid
                            ? <Categories/>
                            : <Navigate to="/login"/>
                    }/>
                    <Route path="/goods" element={
                        isTokenValid
                            ? <Products/>
                            : <Navigate to="/login"/>
                    }/>
                    <Route path="/users" element={
                        !isTokenValid
                            ? <Navigate to="/login"/>
                            : userRole === USER_ROLE_MANAGER
                                ? <Users/>
                                : <Navigate to="/"/>
                    }/>
                </Routes>
            </BrowserRouter>
            <Modal show={showDialog} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>You are using a default password created by your manager. Want to change it?</Modal.Body>
                <Modal.Footer>
                    <Button onClick={handleClose} className="border-red-600 hover:bg-red-600 hover:border-red-600">
                        Proceed with default password
                    </Button>
                    <Button variant="primary" onClick={() => {
                        handleClose();
                        setIsFormShown(true);
                    }}>
                        Change password
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}
