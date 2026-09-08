import { useEffect, useState } from "react";
import UserForm from "./components/UserForm";
import UserTable from "./components/UserTable";

import {
  getAllUsers,
  createUser,
  updateUser,
  deleteUser,
  getUserById,
  addNumbers,
  getStudentInfo,
} from "./services/userService";

function App() {
  const [users, setUsers] = useState([]);
  const [editingUser, setEditingUser] = useState(null);

  const [searchId, setSearchId] = useState("");
  const [searchResult, setSearchResult] = useState(null);

  const [a, setA] = useState("");
  const [b, setB] = useState("");
  const [sum, setSum] = useState(null);

  const [studentInfo, setStudentInfo] = useState("");

  const [loading, setLoading] = useState(false);

  // ==========================
  // GET ALL USERS
  // ==========================
  const loadUsers = async () => {
    try {
      setLoading(true);

      const response = await getAllUsers();

      setUsers(response.data);
    } catch (error) {
      console.error(error);
      alert("Không thể lấy danh sách User!");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  // ==========================
  // CREATE / UPDATE
  // ==========================
  const handleSubmit = async (user) => {
    try {
      if (editingUser) {
        await updateUser(editingUser.id, user);

        alert("Cập nhật User thành công!");

        setEditingUser(null);
      } else {
        await createUser(user);

        alert("Thêm User thành công!");
      }

      await loadUsers();
    } catch (error) {
      console.error(error);
      alert("Có lỗi xảy ra!");
    }
  };

  // ==========================
  // DELETE
  // ==========================
  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(`Bạn có chắc muốn xóa User ID ${id}?`);

    if (!confirmDelete) {
      return;
    }

    try {
      await deleteUser(id);

      alert("Xóa User thành công!");

      await loadUsers();
    } catch (error) {
      console.error(error);
      alert("Xóa User thất bại!");
    }
  };

  // ==========================
  // EDIT
  // ==========================
  const handleEdit = (user) => {
    setEditingUser(user);
  };

  // ==========================
  // SEARCH USER
  // ==========================
  const handleSearch = async () => {
    if (!searchId) {
      alert("Vui lòng nhập ID!");
      return;
    }

    try {
      const response = await getUserById(searchId);

      setSearchResult(response.data);
    } catch (error) {
      setSearchResult(null);
      alert("Không tìm thấy User!");
    }
  };

  // ==========================
  // ADD NUMBER
  // ==========================
  const handleAdd = async () => {
    if (a === "" || b === "") {
      alert("Vui lòng nhập đủ 2 số!");
      return;
    }

    try {
      const response = await addNumbers(a, b);

      setSum(response.data);
    } catch (error) {
      console.error(error);
      alert("Không thể thực hiện phép cộng!");
    }
  };

  // ==========================
  // STUDENT INFO
  // ==========================
  const handleStudentInfo = async () => {
    try {
      const response = await getStudentInfo();

      setStudentInfo(response.data);
    } catch (error) {
      console.error(error);
      alert("Không lấy được thông tin!");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {/* HEADER */}
      <header className="bg-blue-600 px-6 py-5 text-white shadow">
        <div className="mx-auto max-w-7xl">
          <h1 className="text-2xl font-bold">User Management</h1>

          <p className="mt-1 text-blue-100">React JS + Tailwind CSS + JAX-RS</p>
        </div>
      </header>

      <main className="mx-auto max-w-7xl space-y-6 px-6 py-8">
        {/* USER FORM + SEARCH */}
        <div className="grid grid-cols-1 gap-6 lg:grid-cols-3">
          <UserForm
            editingUser={editingUser}
            onSubmit={handleSubmit}
            onCancel={() => setEditingUser(null)}
          />

          <div className="space-y-6 lg:col-span-2">
            {/* SEARCH */}
            <div className="rounded-xl bg-white p-6 shadow-md">
              <h2 className="mb-4 text-xl font-bold">Tìm User theo ID</h2>

              <div className="flex gap-3">
                <input
                  type="number"
                  value={searchId}
                  onChange={(e) => setSearchId(e.target.value)}
                  placeholder="Nhập ID"
                  className="flex-1 rounded-lg border border-gray-300 px-4 py-2 outline-none focus:border-blue-500"
                />

                <button
                  onClick={handleSearch}
                  className="rounded-lg bg-blue-600 px-5 py-2 font-medium text-white hover:bg-blue-700"
                >
                  Tìm kiếm
                </button>
              </div>

              {searchResult && (
                <div className="mt-5 rounded-lg bg-gray-50 p-4">
                  <p>
                    <strong>ID:</strong> {searchResult.id}
                  </p>

                  <p>
                    <strong>Họ tên:</strong> {searchResult.name}
                  </p>

                  <p>
                    <strong>Email:</strong> {searchResult.email}
                  </p>
                </div>
              )}
            </div>

            {/* ADD NUMBER */}
            <div className="rounded-xl bg-white p-6 shadow-md">
              <h2 className="mb-4 text-xl font-bold">Phép cộng</h2>

              <div className="flex flex-wrap items-center gap-3">
                <input
                  type="number"
                  value={a}
                  onChange={(e) => setA(e.target.value)}
                  placeholder="Số A"
                  className="w-32 rounded-lg border border-gray-300 px-4 py-2"
                />

                <span className="text-xl font-bold">+</span>

                <input
                  type="number"
                  value={b}
                  onChange={(e) => setB(e.target.value)}
                  placeholder="Số B"
                  className="w-32 rounded-lg border border-gray-300 px-4 py-2"
                />

                <button
                  onClick={handleAdd}
                  className="rounded-lg bg-green-600 px-5 py-2 font-medium text-white hover:bg-green-700"
                >
                  Tính
                </button>

                {sum !== null && (
                  <span className="text-xl font-bold text-green-600">
                    = {sum}
                  </span>
                )}
              </div>
            </div>

            {/* STUDENT INFO */}
            <div className="rounded-xl bg-white p-6 shadow-md">
              <h2 className="mb-4 text-xl font-bold">Thông tin sinh viên</h2>

              <button
                onClick={handleStudentInfo}
                className="rounded-lg bg-purple-600 px-5 py-2 font-medium text-white hover:bg-purple-700"
              >
                Xem thông tin
              </button>

              {studentInfo && (
                <p className="mt-4 rounded-lg bg-purple-50 p-4 font-medium text-purple-700">
                  {studentInfo}
                </p>
              )}
            </div>
          </div>
        </div>

        {/* USER TABLE */}
        {loading ? (
          <div className="rounded-xl bg-white p-10 text-center shadow">
            Đang tải dữ liệu...
          </div>
        ) : (
          <UserTable
            users={users}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        )}
      </main>
    </div>
  );
}

export default App;
