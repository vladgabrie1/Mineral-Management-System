import { Button } from '@nextui-org/react';
import {useContext} from "react";
import SecurityContext from "../context/SecurityContext.ts";

export const Home = () => {
    const {login} = useContext(SecurityContext)
    return (
        <div className="flex flex-col justify-center items-center min-h-screen text-white">
            <h1 className="text-5xl font-bold animate-pulse mb-4">
                Welcome to Kristal Distribution Group website
            </h1>
            <Button
                variant={"shadow"}
                className="text-2xl mt-5 "
                size="lg"
                color="secondary"
                onClick={login}
            >
                Login
            </Button>
        </div>
    );
};


