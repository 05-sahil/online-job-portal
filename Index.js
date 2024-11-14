document
  .querySelector(".search-container form")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const jobCategory = document.getElementById("job-category").value;
    const searchResults = document.getElementById("search-results");
    searchResults.innerHTML = "";

    let resultsMessage = "";
    switch (jobCategory) {
      case "general":
        resultsMessage = "Showing all available jobs.";
        break;
      case "technical":
        resultsMessage = "Displaying technical job openings.";
        break;
      case "management":
        resultsMessage = "Here are management-related job opportunities.";
        break;
      case "operational":
        resultsMessage = "Explore operational job roles.";
        break;
      default:
        resultsMessage = "Please select a job category.";
    }
    const resultParagraph = document.createElement("p");
    resultParagraph.textContent = resultsMessage;
    searchResults.appendChild(resultParagraph);
  });
