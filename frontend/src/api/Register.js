import api from "./axios";

export const registerUser = (data) => {
  console.log("enter")
  return api.post("/auth/register", data);
};
