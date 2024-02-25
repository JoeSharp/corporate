import Login from "./Login";
import ProjectList from "./ProjectList";
import { useLoginContext } from "./useLogin/useLogin";

function App() {
  const { username } = useLoginContext();

  return (
    <main>
      <h1>Timesheet Application</h1>
      <div>Username: {username}</div>
      {username === undefined ? <Login /> : <ProjectList />}
    </main>
  );
}
export default App;
