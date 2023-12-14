import {Alert, Button, Form} from "react-bootstrap";
import {TfiClose} from "react-icons/tfi";
import {MouseEventHandler} from "react";
import "../styles/form.css";

interface Props {
    title: string
    handleSubmit: React.FormEventHandler<HTMLFormElement>
    isDataCorrect: boolean
    handleCloseButtonClick: MouseEventHandler<HTMLButtonElement>
    startingName: string
    startingDescription: string
}

export default function CategoriesFormWindow({
                                                 title,
                                                 handleSubmit,
                                                 isDataCorrect,
                                                 handleCloseButtonClick,
                                                 startingName,
                                                 startingDescription,
                                             }: Props) {
    return (
        <div className="form-background">
            <div className="form-main">
                <button className="close-icon" onClick={handleCloseButtonClick}>
                    <TfiClose/>
                </button>
                <div className="flex justify-center items-center my-12">
                    <p className="text-2xl font-bold">{title}</p>
                </div>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-4" controlId="name">
                        <Form.Label>Category name</Form.Label>
                        <Form.Control required={true} type="text" name="name" placeholder="Category name..."
                                      autoComplete="off" defaultValue={startingName}/>
                        <Form.Control.Feedback type="invalid">Please enter a category name</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-4" controlId="formPassword">
                        <Form.Label>Category description</Form.Label>
                        <Form.Control type="text" name="description" placeholder="Category description..."
                                      autoComplete="off" defaultValue={startingDescription}/>
                    </Form.Group>
                    {!isDataCorrect && (<Alert variant="danger">Category already exists!</Alert>)}
                    <Button variant="primary" type="submit"
                            className="w-full px-5 py-2 mt-3 bg-our-green font-semibold text-white">
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    );
}