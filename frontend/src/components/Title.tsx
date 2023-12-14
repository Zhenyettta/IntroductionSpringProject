import React from "react";

interface Props extends React.HTMLAttributes<HTMLElement> {
    children: string
}

export default function Title({children, className}: Props) {
    return (
        <div className={className}>
            <div className="relative flex items-center justify-between h-16">
                <h1 className="text-black font-sans text-3xl font-bold">{children}</h1>
            </div>
        </div>
    );
}