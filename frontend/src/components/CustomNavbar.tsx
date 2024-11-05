import {useContext} from "react";
import SecurityContext from "../context/SecurityContext.ts";
import KdgLogo from "../assets/kdg-logo.png";
import {
    Navbar,
    Avatar,
    Dropdown, DropdownItem,
    DropdownMenu,
    DropdownTrigger,
    NavbarBrand,
    NavbarContent,
    NavbarItem, Button
} from "@nextui-org/react";
import {Link} from "react-router-dom";


export default  function CustomNavbar() {
    const {isAuthenticated, logout, loggedInUser, login} = useContext(SecurityContext)

    return (
        <Navbar position="static" isBordered>
            <NavbarBrand>
                <img src={KdgLogo} alt="KDG Logo" className="h-16"/>
            </NavbarBrand>

            {/*Menu available only for logged-in users*/}
            {isAuthenticated() &&(
            <NavbarContent className="gap-4" justify="start">
                <NavbarItem>
                    <Link color="foreground" to="/home">
                        Home
                    </Link>
                </NavbarItem>
                <NavbarItem>
                    <Link to="/make-appointment" aria-current="page">
                        Make Appointment
                    </Link>
                </NavbarItem>
                <NavbarItem>
                    <Link color="foreground" to="/dashboard">
                        Dashboard
                    </Link>
                </NavbarItem>
            </NavbarContent>
            )}



            <NavbarContent as="div" justify="end">

                {isAuthenticated()? (
                <Dropdown placement="bottom-end">
                    <DropdownTrigger>
                        <Avatar
                            isBordered
                            as="button"
                            className="transition-transform"
                            color="secondary"
                            size="sm"
                            src="https://i.pravatar.cc/150?u=a042581f4e29026704d"
                        />
                    </DropdownTrigger>
                    <DropdownMenu aria-label="Profile Actions" variant="flat">
                        <DropdownItem key="profile" className="h-14 gap-2">
                            <p className="font-semibold"> Signed in as <b>{loggedInUser}</b></p>
                        </DropdownItem>
                        <DropdownItem key="logout" onClick={logout} color="danger">
                            Log Out
                        </DropdownItem>
                    </DropdownMenu>
                </Dropdown>
                    ) : (
                    <NavbarItem>
                        <Button color="secondary" variant="shadow" onClick={login}>Login</Button>
                    </NavbarItem>
                )}




            </NavbarContent>
        </Navbar>
    );
}