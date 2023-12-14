import "../styles/card.css";
import Edit from "../assets/edit.png";
import Delete from "../assets/delete.png";
import {Button} from "react-bootstrap";
import {ROLE_KEY, USER_ROLE_MANAGER} from "../utils/constants.ts";

export type CardProps = {
    name: string
    description: string
    onEditButtonClick: React.MouseEventHandler<HTMLButtonElement>
    onDeleteButtonClick: React.MouseEventHandler<HTMLButtonElement>
}

export default function Card({name, description, onEditButtonClick, onDeleteButtonClick}: CardProps) {
    const role = localStorage.getItem(ROLE_KEY);
    return (
        <div className="custom-card">
            <h1 className="text-wrapper">{name}</h1>
            <p className="description">{description}</p>
            {role == USER_ROLE_MANAGER &&
                <>
                    <Button onClick={onEditButtonClick} className="edit"><img alt="Image" src={Edit}/></Button>
                    <Button onClick={onDeleteButtonClick} className="delete"><img alt="Image" src={Delete}/></Button>

                </>
            }
        </div>
    );
}