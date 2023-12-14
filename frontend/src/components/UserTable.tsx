import {MDBDataTable} from 'mdbreact';
import "../styles/table.css";
import {Button} from "react-bootstrap";
import Edit from "../assets/edit.png";
import Delete from "../assets/delete.png";
import {User} from "../utils/types.ts";
import {ROLE_KEY, USER_ROLE_MANAGER} from "../utils/constants.ts";
import {useState} from "react";

interface Props {
    users: User[]
    onEditButtonClick: (id: string) => void
    onDeleteButtonClick: (id: string) => void
}

export default function UserTable({users, onEditButtonClick, onDeleteButtonClick}: Props) {
    const [confirmationMap, setConfirmationMap] = useState<{ [key: string]: boolean }>({});

    function renderButtons(id: string) {
        const showConfirmation = confirmationMap[id] || false;

        return (
            <div>
                <Button
                    onClick={() => onEditButtonClick(id)}
                    className="p-1 rounded-full bg-transparent border-0"
                    style={{ marginRight: '5px' }}
                >
                    <img alt="Image" src={Edit} style={{ height: '2em' }} />
                </Button>
                {localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER && (
                    <Button
                        onClick={() => {
                            setConfirmationMap({ ...confirmationMap, [id]: true });
                        }}
                        className="p-1 rounded-full bg-transparent border-0"
                    >
                        <img alt="Image" src={Delete} style={{ height: '2em' }} />
                    </Button>
                )}
                {showConfirmation && (
                    <div>
                        <p>Will delete. Proceed?</p>
                        <button
                            onClick={() => {
                                onDeleteButtonClick(id);
                                setConfirmationMap({ ...confirmationMap, [id]: false });
                            }}
                        >
                            Yes
                        </button>
                        <button
                            onClick={() => {
                                setConfirmationMap({ ...confirmationMap, [id]: false });
                            }}
                        >
                            No
                        </button>
                    </div>
                )}
            </div>
        );
    }

        return (
            <MDBDataTable
                noBottomColumns
                striped
                bordered
                hover
                data={{
                    columns: [
                        {
                            label: 'Username',
                            field: 'username',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Role',
                            field: 'role',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Actions',
                            field: 'actions',
                            width: 150
                        }
                    ],
                    rows: users.map((user) => ({
                        username: user.username,
                        role: user.role,
                        actions: renderButtons(user.username)
                    }))
                }}
            />
        );
}
