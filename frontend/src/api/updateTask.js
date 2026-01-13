import api from './axios';

export const updateTask = (taskId, task ) => {
  return api.put(`/tasks/${taskId}`,task);
};

