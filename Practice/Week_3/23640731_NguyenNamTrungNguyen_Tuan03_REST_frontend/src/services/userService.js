import axios from "axios";

const API_URL = "/api/user";

// GET tất cả User
export const getAllUsers = () => {
  return axios.get(API_URL);
};

// GET User theo ID
export const getUserById = (id) => {
  return axios.get(`${API_URL}/${id}`);
};

// POST thêm User
export const createUser = (user) => {
  return axios.post(API_URL, user);
};

// PUT cập nhật User
export const updateUser = (id, user) => {
  return axios.put(`${API_URL}/${id}`, user);
};

// DELETE User
export const deleteUser = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

// GET cộng 2 số
export const addNumbers = (a, b) => {
  return axios.get(`${API_URL}/add/${a}/${b}`);
};

// GET thông tin sinh viên
export const getStudentInfo = () => {
  return axios.get(`${API_URL}/view`);
};
