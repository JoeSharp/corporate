import React from "react";

interface UseLogin {
  username: string | undefined;
  accessToken: string | undefined;

  login: (data: FormData) => void;
  logout: () => void;
}

function useLogin(): UseLogin {
  const [username, setUsername] = React.useState<string | undefined>(undefined);
  const [accessToken, setAccessToken] = React.useState<string | undefined>(
    undefined
  );

  const login = React.useCallback((formData: FormData) => {
      fetch("/login", 
          { method: 'POST', body: formData} )
        .then((res) => res.json())
        .then(l => {
          setUsername(l.username);
        setAccessToken(l.accessToken);
        });
  }, []);

  const logout = React.useCallback(() => fetch("/logout"), []);
  
  return {
    username,
    accessToken,
    login,
    logout
  };
}

const DEFAULT_USE_LOGIN: UseLogin = {
  username: 'none',
  accessToken: 'none',
  logout: () => { throw new Error('not implemented') },
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
