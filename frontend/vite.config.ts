import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'
import * as fs from "fs";

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        host: '0.0.0.0',
        port: 5173,
        https: {
            key: fs.readFileSync("certificates/privatekey.pem"),
            cert: fs.readFileSync("certificates/certificate.crt")
        }
    },
    plugins: [react()],
})
