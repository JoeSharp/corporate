import Login from "./Login";
import ProjectList from "./ProjectList";
import useLogin from "./useLogin";

function App() {
  const { username } = useLogin();

  return (
    <main>
      <h1>Timesheet Application</h1>
      <div>Username: {username}</div>
      {username === undefined ? <Login /> : <ProjectList />}
    </main>
  );
}
export default App;
