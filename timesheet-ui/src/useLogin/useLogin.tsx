import React from "react";
import { useQuery } from "@tanstack/react-query";
import { ILoggedInUser } from '../Project';

interface UseLogin {
  username: string | undefined;
  accessToken: string | undefined;

  login: (data: FormData) => void;
}

function useLogin(): UseLogin {
  const [username, setUsername] = React.useState<string | undefined>(undefined);
  const [accessToken, setAccessToken] = React.useState<string | undefined>(
    undefined
  );

  const login = React.useCallback((formData: FormData) => {
      fetch("http://localhost:8080/login", 
          { method: 'POST', body: formData} )
        .then((res) => res.json())
        .then(l => {
          setUsername(l.username);
        setAccessToken(l.accessToken);
        });
  }, []);
  
  return {
    username,
    accessToken,
    login
  };
}

export const LoginContext = React.createContext<UseLogin>(null);
export const useLoginContext = () => React.useContext(LoginContext);

export const LoginContextProvider:React.FC<{children: React.JSXElement}> = ({children}) => {
  const value = useLogin();

  return <LoginContext.Provider value={value}>
    {children}
  </LoginContext.Provider>
}

export default useLogin;
