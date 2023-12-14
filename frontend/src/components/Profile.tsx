import {DownOutlined} from '@ant-design/icons';
import type {MenuProps} from 'antd';
import {Dropdown, Space} from 'antd';
import {ROLE_KEY, USERNAME_KEY} from "../utils/constants.ts";
import {Button} from "react-bootstrap";

const username = localStorage.getItem(USERNAME_KEY);
const role = localStorage.getItem(ROLE_KEY);
interface ProfileProps {
    isFormShown: boolean;
    setIsFormShown: (value: boolean) => void;
}

export default function Profile({ isFormShown, setIsFormShown }: ProfileProps) {

    const handleFormToggle = () => {
        setIsFormShown(!isFormShown);
    };


    const items: MenuProps['items'] = [
        {
            label: (<span>Username: {username}</span>),
            key: '0',
        },
        {
            label: (<span>Role: {role}</span>),
            key: '1',
        },
        {
            type: 'divider',
        },
        {
            label: (
                <Button variant="contained" color='red' onClick={handleFormToggle}>
                    Change password
                </Button>
            ),
            key: '2',
        }
    ];

    return (
        <Dropdown menu={{items}} >
            <div >
                <span className="text-1xl font-bold text-black mr-10">
                  <Space>
                    My profile
                    <DownOutlined/>
                  </Space>
                </span>

            </div>

        </Dropdown>

    );

}
