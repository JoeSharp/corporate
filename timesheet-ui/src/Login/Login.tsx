import React from "react";

import { useLoginContext } from '../useLogin/useLogin';

const Login: React.FC = () => {
  const formRef = React.useRef();

  const {login} = useLoginContext();

  const onSubmit = React.useCallback(e => {
    let formData = new FormData(formRef.current);
    login(formData);
    e.preventDefault();

  }, [login]);

  return <form ref={formRef} onSubmit={onSubmit}>
    <input type='text' name='username' placeholder='username' />
    <input type='password' name='password' />

    <button type='submit'>Login</button>
  </form>;
};

export default Login;
