import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../api/Authapi";
import { Input , Button , Form } from "antd";
function LoginPage() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  // function handleChange(e) {
  //   const { name, value } = e.target;

  //   setFormData((prev) => ({
  //     ...prev,
  //     [name]: value,
  //   }));
  // }

  async function handleSubmit(e) {
    // e.preventDefault();
    console.log("response")
    console.log(e)
    

    try {
      console.log("api calling")
      const response = await loginUser(e);
       console.log(response) 
       const role = response.data.role
       const token = response.data.jwtToken
       localStorage.setItem("token",token)
       if(role == "ADMIN") navigate("/admin");
       else if(role == "EMPLOYEE") navigate("/employee");
       else navigate("/manager")
    } catch (error) {
      alert("error");
    }
  }

  return (
    <Form className="form" initialValues={{role:"Employee"}} onFinish={handleSubmit}>
          <Form.Item style={{width:300}} label = 'Email' name='email'>
            <Input placeholder="Email" ></Input>
          </Form.Item>
          <Form.Item style={{width:300}} label = 'Password' name='password'>
            <Input.Password placeholder="Password" ></Input.Password>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
        </Form>
  );
}

export default LoginPage;
