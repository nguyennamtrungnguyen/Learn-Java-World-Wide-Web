<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String today = LocalDate.now().toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://cdn.tailwindcss.com"></script>

    <title>Exercise 04 - Register Form</title>
</head>

<body class="min-h-screen bg-gray-100 flex items-center justify-center py-10">

<div class="bg-white w-full max-w-2xl p-8 rounded-xl shadow-lg">

    <form action="${pageContext.request.contextPath}/processFormUpload"
          method="post"
          enctype="multipart/form-data"
          class="space-y-5">

        <h1 class="text-center font-bold text-2xl text-gray-800 mb-6">
            HTML Form Example with File Upload
        </h1>

        <!-- NAME -->
        <div>
            <label for="name" class="block font-medium text-gray-700 mb-1">
                Name:
            </label>

            <input
                    type="text"
                    id="name"
                    name="name"
                    class="w-full border border-gray-300 p-2.5 rounded-lg
                           focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
            />
        </div>

        <!-- PASSWORD -->
        <div>
            <label for="password" class="block font-medium text-gray-700 mb-1">
                Password:
            </label>

            <input
                    type="password"
                    id="password"
                    name="password"
                    class="w-full border border-gray-300 p-2.5 rounded-lg
                           focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
            />
        </div>

        <!-- GENDER -->
        <div>
            <label class="block font-medium text-gray-700 mb-2">
                Gender:
            </label>

            <div class="flex gap-6">
                <label class="flex items-center gap-2">
                    <input
                            type="radio"
                            name="gender"
                            value="male"
                            checked
                    />
                    <span>Male</span>
                </label>

                <label class="flex items-center gap-2">
                    <input
                            type="radio"
                            name="gender"
                            value="female"
                    />
                    <span>Female</span>
                </label>
            </div>
        </div>

        <!-- HOBBIES -->
        <div>
            <label class="block font-medium text-gray-700 mb-2">
                Hobbies:
            </label>

            <div class="flex flex-wrap gap-5">
                <label class="flex items-center gap-2">
                    <input
                            type="checkbox"
                            name="hobbies"
                            value="reading"
                    />
                    <span>Reading</span>
                </label>

                <label class="flex items-center gap-2">
                    <input
                            type="checkbox"
                            name="hobbies"
                            value="sports"
                    />
                    <span>Sports</span>
                </label>

                <label class="flex items-center gap-2">
                    <input
                            type="checkbox"
                            name="hobbies"
                            value="music"
                    />
                    <span>Music</span>
                </label>
            </div>
        </div>

        <!-- COUNTRY -->
        <div>
            <label for="country" class="block font-medium text-gray-700 mb-1">
                Country:
            </label>

            <select
                    id="country"
                    name="country"
                    class="w-full border border-gray-300 p-2.5 rounded-lg
                           focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
                <option value="vietnam">Vietnam</option>
                <option value="japan">Japan</option>
                <option value="laos">Laos</option>
                <option value="china">China</option>
                <option value="southkorea">South Korea</option>
            </select>
        </div>

        <!-- BIRTH DATE -->
        <div>
            <label for="birthDate" class="block font-medium text-gray-700 mb-1">
                Birth Date:
            </label>

            <input
                    type="date"
                    id="birthDate"
                    name="birthDate"
                    value="<%= today %>"
                    class="w-full border border-gray-300 p-2.5 rounded-lg
                           focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>

        <!-- ABOUT -->
        <div>
            <label for="about" class="block font-medium text-gray-700 mb-1">
                About:
            </label>

            <textarea
                    id="about"
                    name="about"
                    rows="4"
                    class="w-full border border-gray-300 p-2.5 rounded-lg
                           focus:outline-none focus:ring-2 focus:ring-blue-500
                           resize-none"
            ></textarea>
        </div>

        <!-- PROFILE PICTURE -->
        <div>
            <label for="profilePic" class="block font-medium text-gray-700 mb-2">
                Profile Picture:
            </label>

            <input
                    type="file"
                    id="profilePic"
                    name="profilePic"
                    accept="image/*"
                    class="w-full border border-gray-300 p-2 rounded-lg
                           bg-white"
            />
        </div>

        <!-- BUTTON -->
        <button
                type="submit"
                class="w-full bg-blue-500 text-white font-medium
                       py-2.5 rounded-lg
                       hover:bg-blue-600
                       transition duration-200"
        >
            Submit
        </button>

    </form>

</div>

</body>
</html>