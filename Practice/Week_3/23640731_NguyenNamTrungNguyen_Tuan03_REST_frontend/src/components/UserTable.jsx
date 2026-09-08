function UserTable({ users, onEdit, onDelete }) {
  return (
    <div className="overflow-hidden rounded-xl bg-white shadow-md">
      <div className="border-b px-6 py-4">
        <h2 className="text-xl font-bold text-gray-800">Danh sách User</h2>
      </div>

      <div className="overflow-x-auto">
        <table className="w-full">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-6 py-3 text-left text-sm font-semibold">ID</th>

              <th className="px-6 py-3 text-left text-sm font-semibold">
                Họ tên
              </th>

              <th className="px-6 py-3 text-left text-sm font-semibold">
                Email
              </th>

              <th className="px-6 py-3 text-center text-sm font-semibold">
                Thao tác
              </th>
            </tr>
          </thead>

          <tbody>
            {users.length === 0 ? (
              <tr>
                <td colSpan="4" className="px-6 py-8 text-center text-gray-500">
                  Không có User
                </td>
              </tr>
            ) : (
              users.map((user) => (
                <tr key={user.id} className="border-t hover:bg-gray-50">
                  <td className="px-6 py-4">{user.id}</td>

                  <td className="px-6 py-4 font-medium">{user.name}</td>

                  <td className="px-6 py-4 text-gray-600">{user.email}</td>

                  <td className="px-6 py-4">
                    <div className="flex justify-center gap-2">
                      <button
                        onClick={() => onEdit(user)}
                        className="rounded-lg bg-yellow-500 px-4 py-2 text-sm font-medium text-white hover:bg-yellow-600"
                      >
                        Sửa
                      </button>

                      <button
                        onClick={() => onDelete(user.id)}
                        className="rounded-lg bg-red-600 px-4 py-2 text-sm font-medium text-white hover:bg-red-700"
                      >
                        Xóa
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default UserTable;
