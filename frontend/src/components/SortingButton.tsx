import React from 'react';
import {FaSortUp, FaSortDown} from 'react-icons/fa';
import {Button} from "react-bootstrap";

interface Props extends React.HTMLAttributes<HTMLElement> {
    ascending: boolean;
    onClick: React.MouseEventHandler<HTMLButtonElement>;
}

export default function SortingButton({ascending, onClick, className}: Props) {
    return (
        <Button variant="primary" onClick={onClick} className={className}>
            {ascending ? <FaSortUp/> : <FaSortDown/>}
        </Button>
    );
}