//const todoSubmit = document.getElementById("todo-submit");
const todoList = document.getElementById("todo-list");
const filterOptions = document.getElementsByName("filter");
const todoForm = document.getElementById("todo-form");
//const todos = document.getElementsByName("todo-item");

!localStorage.getItem("filterBy") ||
  localStorage.getItem("filterBy") === "undefined"
  ? window.localStorage.setItem("filterBy", "all")
  : null;

filterTodos();
filterOptions.forEach((item) =>
  item.value === localStorage.getItem("filterBy")
    ? (item.checked = true)
    : (item.checked = false)
);

//todoList.addEventListener("click", handleTodoClickEvent);
filterOptions.forEach((option) =>
  option.addEventListener("click", filterTodos)
);

console.log(todoForm);

function filterTodos() {
  const value = this.value || window.localStorage.getItem("filterBy");
  const todoListItems = document.querySelectorAll(".todo-item");

  todoListItems.forEach((item) =>
    item.value === value ? (item.checked = true) : (item.checked = false)
  );

  console.log(todoListItems);

  switch (value) {
    case "all":
      todoListItems.forEach((item) => {
        item.style.display = "grid";
        item.parentNode.parentNode.parentNode.style.display = "grid";
      });
      break;
    case "completed":
      todoListItems.forEach((item) => {
        if (!item.classList.contains("completed")) {
          item.style.display = "none";
          item.parentNode.parentNode.parentNode.style.display = "none";
          // item.parentNode.parentNode.parentNode.getElementsByClassName("btn_delete")[0].style.display = "none";
        } else {
          item.style.display = "grid";
          // item.parentNode.parentNode.parentNode.getElementsByClassName("btn_delete")[0].style.display = "grid";
          item.parentNode.parentNode.parentNode.style.display = "grid";
        }
      }
      );
      break;
    case "uncompleted":
      todoListItems.forEach((item) =>
        todoListItems.forEach((item) => {
          if (item.classList.contains("completed")) {
            item.style.display = "none";
            item.parentNode.parentNode.parentNode.style.display = "none";
          } else {
            item.style.display = "grid";
            item.parentNode.parentNode.parentNode.style.display = "grid";
          }
        }
        ));

      break;
    default:
      todoListItems.forEach((item) => (item.style.display = "grid"));
      break;
  }
}