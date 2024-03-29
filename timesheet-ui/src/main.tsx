import React from "react";
import ReactDOM from "react-dom/client";
import { LoginContextProvider } from './useLogin/useLogin';
import App from "./App.tsx";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <LoginContextProvider>
        <App />
      </LoginContextProvider>
    </QueryClientProvider>
  </React.StrictMode>
);
