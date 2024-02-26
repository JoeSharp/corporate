import React, { FormEventHandler } from "react";

import { useLoginContext } from '../useLogin/useLogin';

const Login: React.FC = () => {
  const formRef = React.useRef<HTMLFormElement>(null);

  const {login} = useLoginContext();

  const onSubmit: FormEventHandler = React.useCallback((e) => {
    e.preventDefault();
    if (!formRef.current) return;

    const formData = new FormData(formRef.current);
    login(formData);

  }, [login]);

  return <form ref={formRef} onSubmit={onSubmit}>
    <input type='text' name='username' placeholder='username' />
    <input type='password' name='password' />

    <button type='submit'>Login</button>
  </form>;
};

export default Login;
