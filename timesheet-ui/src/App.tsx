import ProjectList from "./ProjectList";
import { useLoginContext } from "./useLogin/useLogin";

function App() {
  const { username, logout } = useLoginContext();

  return (
    <main>
      <h1>Timesheet Application</h1>
      <div>Username: {username}</div>
      <button onClick={logout}>Logout</button>
      <ProjectList />
    </main>
  );
}
export default App;
