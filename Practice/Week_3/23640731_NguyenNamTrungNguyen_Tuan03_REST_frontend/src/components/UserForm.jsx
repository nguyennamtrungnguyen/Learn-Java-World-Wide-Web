import { useEffect, useState } from "react";

function UserForm({ editingUser, onSubmit, onCancel }) {
  const [form, setForm] = useState({
    id: "",
    name: "",
    email: "",
  });

  useEffect(() => {
    if (editingUser) {
      setForm({
        id: editingUser.id,
        name: editingUser.name,
        email: editingUser.email,
      });
    } else {
      setForm({
        id: "",
        name: "",
        email: "",
      });
    }
  }, [editingUser]);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    onSubmit({
      id: Number(form.id),
      name: form.name,
      email: form.email,
    });
  };

  return (
    <div className="rounded-xl bg-white p-6 shadow-md">
      <h2 className="mb-5 text-xl font-bold text-gray-800">
        {editingUser ? "Cập nhật User" : "Thêm User"}
      </h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="mb-1 block text-sm font-medium text-gray-700">
            ID
          </label>

          <input
            type="number"
            name="id"
            value={form.id}
            onChange={handleChange}
            disabled={!!editingUser}
            required
            className="w-full rounded-lg border border-gray-300 px-4 py-2 outline-none focus:border-blue-500 disabled:bg-gray-100"
          />
        </div>

        <div>
          <label className="mb-1 block text-sm font-medium text-gray-700">
            Họ tên
          </label>

          <input
            type="text"
            name="name"
            value={form.name}
            onChange={handleChange}
            required
            placeholder="Nhập họ tên"
            className="w-full rounded-lg border border-gray-300 px-4 py-2 outline-none focus:border-blue-500"
          />
        </div>

        <div>
          <label className="mb-1 block text-sm font-medium text-gray-700">
            Email
          </label>

          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            required
            placeholder="example@gmail.com"
            className="w-full rounded-lg border border-gray-300 px-4 py-2 outline-none focus:border-blue-500"
          />
        </div>

        <div className="flex gap-3">
          <button
            type="submit"
            className="rounded-lg bg-blue-600 px-5 py-2 font-medium text-white hover:bg-blue-700"
          >
            {editingUser ? "Cập nhật" : "Thêm User"}
          </button>

          {editingUser && (
            <button
              type="button"
              onClick={onCancel}
              className="rounded-lg bg-gray-500 px-5 py-2 font-medium text-white hover:bg-gray-600"
            >
              Hủy
            </button>
          )}
        </div>
      </form>
    </div>
  );
}

export default UserForm;
