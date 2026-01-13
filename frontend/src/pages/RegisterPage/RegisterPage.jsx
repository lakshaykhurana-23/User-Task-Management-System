import { useState } from "react";
import { registerUser } from "../../api/Register";
import { useNavigate } from "react-router-dom";
import { Form , Button , Input , Select } from "antd";


function RegisterPage() {
  const options = [
  {
    value: 'ADMIN',
    label: 'Admin',
  },
  {    value: 'EMPLOYEE',
    label: 'Employee',
  },
  {
    value: 'MANAGER',
    label: 'Manager',
  }
];
  const navigate = useNavigate();
  // const [formData, setFormData] = useState({
  //   name: "",
  //   email: "",
  //   password: "",
  //   role: "EMPLOYEE",
  // });

  // function handleChange(e) {
  //   const { name, value } = e.target;

  //   setFormData((prev) => ({
  //     ...prev,
  //     [name]: value,
  //   }));
  // }

  async function handleSubmit(e) {
    console.log(e);
    console.log(typeof(e))
    console.log(typeof(e.name))


    try {
      const response = await registerUser(e);
      console.log(response);
      console.log(typeof(response))
      navigate("/login");
    } catch (error) {
      alert("Error occurred");
    }
  }

  return (
    <Form className="form" initialValues={{role:"Employee"}} onFinish={handleSubmit}>
      <Form.Item label = 'Name' name='name'>
        <Input style={{width:300}} placeholder="Name" ></Input>
      </Form.Item>
      <Form.Item style={{width:300}} label = 'Email' name='email'>
        <Input placeholder="Email" ></Input>
      </Form.Item>
      <Form.Item style={{width:300}} label = 'Password' name='password'>
        <Input.Password placeholder="Password" ></Input.Password>
      </Form.Item>
      <Form.Item style={{width:300}} label = 'Role' name='role'>
        <Select options={options}></Select>
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit">
          Register
        </Button>
      </Form.Item>
    </Form>

    /* <form onSubmit={handleSubmit}>
      <h2>Register</h2>

      <input
        type="text"
        name="name"
        placeholder="Name"
        value={formData.name}
        onChange={handleChange}
      />

      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
      />

      <input
        type="password"
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleChange}
      />

      <select name="role" value={formData.role} onChange={handleChange}>
        <option value="EMPLOYEE">Employee</option>
        <option value="MANAGER">Manager</option>
        <option value="ADMIN">Admin</option>
      </select>

      <button type="submit">Register</button>
    </form> */

  );
}

export default RegisterPage;
