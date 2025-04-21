import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/products': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/products/, '/products'),
      },
      '/auth': {
        target: 'http://localhost:8080', // Proxy requests to the backend
        changeOrigin: true, // Change the origin of the request to the target URL
        rewrite: (path) => path.replace(/^\/auth/, '/auth'), // Rewrite the path if necessary
      },
    },
  },
});