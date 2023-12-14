import {Col, Row} from "react-bootstrap";
import Card from "./Card.tsx";
import {Category} from "../utils/types.ts";
import { useNavigate } from 'react-router-dom';


interface CardGridProps {
    categories: Category[]
    onEditButtonClick: (id: number) => void
    onDeleteButtonClick: (id: number) => void
}

export default function CardGrid({categories, onEditButtonClick, onDeleteButtonClick}: CardGridProps) {
    const navigate = useNavigate();

    const handleCategoryClick = (categoryId: number) => {
        navigate(`/goods?category=${categoryId}`);
    };


    return (
        <div className="mt-5">
            <Row xs={1} md={3} className="g-4">
                {categories.map((category) => (
                    <Col key={category.id} className="d-flex align-items-center justify-content-center mb-2.5">
                        <div onClick={() =>handleCategoryClick(category.id)}>
                            <Card
                                name={category.name}
                                description={category.description}
                                onEditButtonClick={(event) => {
                                    event.stopPropagation();
                                    onEditButtonClick(category.id);
                                }}
                                onDeleteButtonClick={(event) => {
                                    event.stopPropagation();

                                    const confirmation = window.confirm('Will delete. Proceed?');

                                    if (confirmation) {
                                        onDeleteButtonClick(category.id);
                                    }
                                }}

                            />
                        </div>

                    </Col>
                ))}
            </Row>
        </div>
    );
}