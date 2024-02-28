import React from "react";
import { useQuery } from "@tanstack/react-query";

interface UseLogin {
  username: string | undefined;

  logout: () => void;
}

function useLogin(): UseLogin {

  const {
    data: username = 'Anonymous',
  } = useQuery<string, Error>({
    queryFn: () =>
      fetch("/auth/getUser").then((res) => res.text()),
  });

  const logout = React.useCallback(() => fetch("/auth/logout"), []);
  
  return {
    username,
    logout
  };
}

const DEFAULT_USE_LOGIN: UseLogin = {
  username: 'none',
  logout: () => { throw new Error('not implemented') },
}
export const LoginContext = React.createContext<UseLogin>(DEFAULT_USE_LOGIN);
export const useLoginContext = () => React.useContext(LoginContext);

export const LoginContextProvider:React.FC<{children: React.JSX.Element}> = ({children}) => {
  const value = useLogin();

  return <LoginContext.Provider value={value}>
    {children}
  </LoginContext.Provider>
}

export default useLogin;
