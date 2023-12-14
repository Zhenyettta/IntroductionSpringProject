import MainPicture from "../components/MainPicture.tsx";
import {TypeAnimation} from 'react-type-animation';

export default function HomePage() {
    return (
        <div>
            <h1 className="text-center font-bold mt-5">Овочі для овочів</h1>
            <div className="text-center font-bold "><TypeAnimation
                sequence={[
                    '(для Даші)',
                    1000,
                    '(для Артема)',
                    1000,
                    '(для Трохима)',
                    1000,
                ]}
                speed={50}
                style={{fontSize: '2em'}}
                repeat={Infinity}
            />
            </div>

            <MainPicture/>
        </div>
    );
}