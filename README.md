# Android-chat
שלום!
להלן הנחיות הרצה:
ראשית צריך לפתוח את האנדרואיד סטודיו ולעשות clone לפרויקט הנוכחי. כאשר הclone יסתיים ניתן להריץ את האפליקציה.

שנית צריך לפתוח חלון של visual studio ולעשות clone לרפוזיטורי בכתובת https://github.com/Yosef-Perelman/chat-server שבו נמצא השרת. צירפנו אתכם כcollaborator 
גם אליו. כאשר הclone יסתיים, בעמודה השמאלית איפה שהsolution יש להיכנס ל Chat-App.sln. 
לאחר מכן ניתן להריץ את השרת. השרת ירוץ בכתובת localhost:5287.

תוכנת הלקוח נמצאת בכתובת https://github.com/Yosef-Perelman/react-api (זה זהה למה שהגשנו בחלק 2 אם זה רלוונטי). כדי להריץ אותה צריך לעשות clone 
לרפוזיטורי לתוך פרויקט בvscode. אחרי שעושים clone, צריך בטרמינל לרשום את הפקודה cd react-api כדי להיכנס לתיקיה. 
לאחר מכן יש לרשום את הפקודות הבאות:
npm install</br>
npm install react-router-dom</br>
npm install react-bootstrap</br>
npm install @microsoft/signalr</br>
ואז npm start ותוכנת הלקוח תעלה. כעת ניתן להשתמש בה, והיא מסונכרנת עם השרת.

הערות:
1. מבחינת הapi, הוא זהה לתרגיל 2 (יש רשימה בreadMe שכאן: https://github.com/Yosef-Perelman/Chat-App).
2. לשם הבדיקה יש משתמש רשום hard-coded ששם המשתמש הוא "Ariel" והסיסמה היא "1234". ניתן כמובן גם להירשם עם משתמש חדש.
3. האפליקציה עובדת אך ורק מול השרת localhost:5287. שרתים אחרים לא נתמכים.
