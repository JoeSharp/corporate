import React from "react";

interface UseLogin {
  username: string | undefined;
  accessToken: string | undefined;

  login: (username: string, password: string) => void;
}

function useLogin(): UseLogin {
  const [username, setUsername] = React.useState<string | undefined>(undefined);
  const [accessToken, setAccessToken] = React.useState<string | undefined>(
    undefined
  );

  const login = React.useCallback((uname: string, pword: string) => {}, []);

  return {
    username,
    accessToken,
    login,
  };
}

export default useLogin;
