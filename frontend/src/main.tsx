import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {NextUIProvider} from '@nextui-org/react'

createRoot(document.getElementById('root')!).render(
    <NextUIProvider>
        <main className="dark text-foreground bg-background">
            <App/>
        </main>
    </NextUIProvider>
)
