import {ReactNode, useContext} from 'react'
import SecurityContext from '../context/SecurityContext.ts'
import {Button} from "@nextui-org/react";

export interface RouteGuardProps {
    children: ReactNode
}

export function RouteGuard({children}: RouteGuardProps) {
    const {isAuthenticated, login} = useContext(SecurityContext)

    if (isAuthenticated()) {
        return children
    } else {
        return (
            <div
                className="flex flex-col items-center justify-center h-screen"
            >
                <h1
                    className="text-6xl text-red-400 text-center mb-6"
                >
                    {isAuthenticated()
                        ? "You don't have permission to access this page"
                        : "You need to log in to access this page"}
                </h1>
                <h1
                    className="text-gray-300 text-center mb-4"
                >
                    Please check your credentials or contact support if you believe this is a mistake.
                </h1>
                <Button
                    variant={"shadow"}
                    color="secondary"
                    className="mt-4"
                    onClick={login}
                >
                    Go to Login
                </Button>
            </div>
        );
    }
}