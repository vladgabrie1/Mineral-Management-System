import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import SecurityContextProvider from "./context/SecurityContextProvider.tsx";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import CustomNavbar from "./components/CustomNavbar.tsx";
import {Dashboard} from "./components/Dashboard.tsx";
import {MakeAppointment} from "./components/MakeAppointment.tsx";
import {RouteGuard} from "./components/RouteGuard.tsx";
import axios from "axios";
import "./App.css"
import {Home} from "./components/Home.tsx";

axios.defaults.baseURL = "http://localhost:8090"
const queryClient = new QueryClient()

export default function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <SecurityContextProvider>
                <BrowserRouter>
                    <CustomNavbar/>
                    <Routes>
                        <Route path="/make-appointment" element={<RouteGuard><MakeAppointment/></RouteGuard>}/>
                        <Route path="/" element={<Navigate to="/home"/>}/>
                        <Route path="/dashboard" element={<RouteGuard><Dashboard/></RouteGuard>}/>
                        <Route path={"/home"} element={<Home/>}/>
                    </Routes>
                </BrowserRouter>
            </SecurityContextProvider>
        </QueryClientProvider>
    );
}