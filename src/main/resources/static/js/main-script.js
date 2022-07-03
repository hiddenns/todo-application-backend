const todoSubmit = document.getElementById("todo-submit");
const todoList = document.getElementById("todo-list");
const filterOptions = document.getElementsByName("filter");
const todos = document.getElementsByName("todo-item");

filterTodos();
filterOptions.forEach((item) =>
  item.value === localStorage.getItem("filterBy")
    ? (item.checked = true)
    : (item.checked = false)
);

todoList.addEventListener("click", handleTodoClickEvent);
filterOptions.forEach((option) =>
  option.addEventListener("click", filterTodos)
);


function filterTodos() {
  const value = this.value || window.localStorage.getItem("filterBy");
  const todoListItems = document.querySelectorAll(".todo-item");

  todoListItems.forEach((item) =>
    item.value === value ? (item.checked = true) : (item.checked = false)
  );
  switch (value) {
    case "all":
      todoListItems.forEach((item) => (item.style.display = "grid"));
      break;
    case "completed":
      todoListItems.forEach((item) =>
        !item.classList.contains("completed")
          ? (item.style.display = "none")
          : (item.style.display = "grid")
      );
      break;
    case "uncompleted":
      todoListItems.forEach((item) =>
        item.classList.contains("completed")
          ? (item.style.display = "none")
          : (item.style.display = "grid")
      );
      break;
    default:
      todoListItems.forEach((item) => (item.style.display = "grid"));
      break;
  }
}