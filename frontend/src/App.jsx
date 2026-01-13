import './App.css'
import { Route , Routes , Navigate } from 'react-router-dom'
import RegisterPage from './pages/RegisterPage/RegisterPage'
import LoginPage from './pages/LoginPage'
import { Link } from 'react-router-dom'
import AdminPage from './pages/Admin/AdminPage'
import EmployeePage from './pages/Employee/EmployeePage'
import ManagerPage from './pages/Manager/ManagerPage'
import UserPage from './pages/Users/UserPage.jsx'
import AllTaskPage from './pages/Tasks/AllTasksPage.jsx'
import MyTaskspage from './pages/MyTasks/MyTaskspage.jsx'
function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/register" />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/admin" element={<AdminPage />} />
        <Route path="/employee" element={<EmployeePage />} />
        <Route path="/manager" element={<ManagerPage />} />
        <Route path="/users" element={<UserPage />} />
        <Route path="/tasks" element={<AllTaskPage />} />
        <Route path="/tasks/my" element={<MyTaskspage />} />
      </Routes>

      <Link to></Link>
    </>
  )
}

export default App
