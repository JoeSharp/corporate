import React from "react";

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

const DEFAULT_USE_LOGIN: UseLogin = {
  username: 'none',
  accessToken: 'none',
  login: () => { throw new Error('not implemented') }
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
