import {Disclosure, Menu} from "@headlessui/react";
import Logo from "./Logo.tsx";
import {Link, useLocation} from "react-router-dom";
import "../styles/navigation.css";
import Profile from "./Profile.tsx";
import {EXPIRES_AT_KEY, ROLE_KEY, USER_ROLE_EMPLOYEE} from "../utils/constants.ts";
import {handleLogout} from "../utils/sharedFunctions.ts";
import ChangePasswordForm from "./ChangePasswordForm.tsx";
import React from "react";

interface NavigationItem {
    name: string;
    href: string;
}

let navigation: NavigationItem[];

if (localStorage.getItem(ROLE_KEY) === USER_ROLE_EMPLOYEE) {
    navigation = [
        {name: "Categories", href: "/categories"},
        {name: "Goods", href: "/goods"},
    ];
} else {
    navigation = [
        {name: "Categories", href: "/categories"},
        {name: "Goods", href: "/goods"},
        {name: "Users", href: "/users"},
    ];
}


function classNames(...classes: string[]): string {
    return classes.filter(Boolean).join(" ");
}

interface Props {
    isFormShown: boolean
    setIsFormShown: React.Dispatch<React.SetStateAction<boolean>>
}

export default function NavigationBar({isFormShown, setIsFormShown}: Props) {
    const location = useLocation();


    const tokenExpiresAt = parseInt(localStorage?.getItem(EXPIRES_AT_KEY) ?? "0", 10) * 1000;
    const isTokenValid = tokenExpiresAt > Date.now();

    // return (
    //     <Navbar className="bg-transparent">
    //         <Container>
    //             <Navbar.Brand href="/"><Logo/></Navbar.Brand>
    //             <Navbar.Toggle/>
    //             <Navbar.Collapse id="basic-navbar-nav">
    //                 <Nav variant="pills">
    //                     <Nav.Item>
    //                         <Nav.Link href="/categories">Categories</Nav.Link>
    //                     </Nav.Item>
    //                     <Nav.Item>
    //                         <Nav.Link href="/goods">Products</Nav.Link>
    //                     </Nav.Item>
    //                 </Nav>
    //             </Navbar.Collapse>
    //             <Navbar.Collapse className="justify-content-end">
    //                 <Navbar.Text>
    //                     Signed in as: <a href="#login">Mark Otto</a>
    //                 </Navbar.Text>
    //             </Navbar.Collapse>
    //         </Container>
    //     </Navbar>
    // );

    return (
        <Disclosure as="nav">
            <>
                <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
                    <div className="relative flex items-center justify-between h-16">
                        <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
                            <Disclosure.Button
                                className="inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                                <span className="sr-only">Open main menu</span>

                            </Disclosure.Button>
                        </div>
                        <div className="flex-1 flex items-center justify-center sm:items-stretch sm:justify-start">
                            <Link to="/" className="flex-shrink-0 flex items-center select-none no-underline ">
                                <Logo/>
                            </Link>
                            <div className="hidden sm:block sm:ml-6">
                                <div className="flex space-x-4">
                                    {navigation.map((item) => (
                                        <Link
                                            key={item.name}
                                            to={item.href}
                                            className={classNames(
                                                location.pathname === item.href
                                                    ? "bg-gray-900 text-white underline-offset-none no-underline"
                                                    : "text-black hover:bg-gray-700 hover:text-white underline-offset-none no-underline",
                                                "px-3 py-2 rounded-md text-sm font-medium"
                                            )}
                                            aria-current={
                                                location.pathname === item.href ? "page" : undefined
                                            }
                                        >
                                            {item.name}
                                        </Link>
                                    ))}
                                </div>
                            </div>
                        </div>
                        {isTokenValid && (
                            <div
                                className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                                <Profile isFormShown={isFormShown} setIsFormShown={setIsFormShown}/>
                                {isFormShown && <ChangePasswordForm handleClose={() => setIsFormShown(false)}/>}
                                <Menu as="div" className="ml-3 relative">
                                    <div>
                                        <span className="sr-only">Open user menu</span>
                                        <button
                                            className={"inline-flex items-center justify-center p-2 rounded-md text-white hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white bg-red-500"}
                                            onClick={handleLogout}
                                            title="Sign out"
                                        >
                                            Log out
                                        </button>
                                    </div>
                                </Menu>
                            </div>
                        )}
                    </div>
                </div>
                <Disclosure.Panel className="sm:hidden">
                    <div className="px-2 pt-2 pb-3 space-y-1">
                        {navigation.map((item) => (
                            <Link
                                key={item.name}
                                to={item.href}
                                className={classNames(
                                    location.pathname === item.href
                                        ? "bg-gray-900 text-white"
                                        : "text-gray-300 hover:bg-gray-700 hover:text-white",
                                    "block px-3 py-2 rounded-md text-base font-medium"
                                )}
                                aria-current={location.pathname === item.href ? "page" : undefined}
                            >
                                {item.name}
                            </Link>
                        ))}
                    </div>
                </Disclosure.Panel>

            </>

        </Disclosure>

    );
}