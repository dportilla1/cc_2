import memberPic from "@static/images/Placeholder.jpg";
import teamPic from "@static/images/team_pfp_resized.png";
import davidsPic from "@static/images/cropped_profile_david.png";
import ginaPic from "@static/images/gina_image.png";
import maggiePic from "@static/images/MaggieRingProfileImage.png";
import jessicaPic from "@static/images/jessica_pfp_cropped.png";
import petersPic from "@static/images/petersPic.png";

export const teamData =
    {
        teamName: "The Power Coders",
        missionStatement: "Uniting our wide-reaching skills to create clean solutions to complex problems.",
        imagePath: teamPic,
    };


export const memberData = [
    {
        name: "Jessica Lee",
        bio: "Hello! My name is Jessica Lee, I'm a senior here at CSU pursuing a Data Science degree with a concentration in Computer Science. My ultimate goal is to become a data scientist and working with machine learning or artificial intelligence in virtual reality. My biggest achievement is creating a machine learning image processor graphical user interface that detected and counted the overlap of stained neuron images to help neuroscientists save time.",
        homeTown: "Springfield, IL",
        imagePath: jessicaPic
    },
    {
        name: "Gina Lee",
        bio: "Hello! My name is Gina Lee, I'm currently a senior at CSU, and my major is in Computer science with a general concentration. My ultimate goal is to become a software developer in the future. My biggest achievement was a group project I did that was able to detect certain emotions that were being conveyed in a text.",
        homeTown: "Aurora, CO",
        imagePath: ginaPic
    },
    {
        name: "Peter Caprio",
        bio: "Hey! My name is Peter Caprio, I'm a 4th year Computer Science student at CSU. My ultimate goal is to go into the Machine Learing space and apply my knowledge to the environmental sciences. My biggest achievement thus far has been a machine learning algorithm that predicts the timing of salmon migrations in California",
        homeTown: "Lafayette, CA",
        imagePath: petersPic
    },
    {
        name: "David Portilla",
        bio: "Greetings! My name is David, I'm a senior at Colorado State University with a concentration in software development. My ultimate goal is to work for the Electronic Frontier Foundation. My biggest achievement is creating an app to recommend songs and artists based on your Rateyourmusic profile.",
        homeTown: "Denver, CO",
        imagePath: davidsPic
    },
    {
        name: "Maggie Ring",
        bio: "Hi! My name is Maggie, I'm in my third year studying Computer Science with a concentration in Cybersecurity. My ultimate goal is to work in Cybersecurity and help secure important systems. My biggest accomplishment is the USB rubber ducky I programmed to create a reverse shell when plugged in.",
        homeTown: "Fort Collins, CO",
        imagePath: maggiePic
    },
];
