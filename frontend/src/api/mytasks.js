import api from "./axios";

export const myTasks = () => {
  return api.get("/tasks/my");
};
