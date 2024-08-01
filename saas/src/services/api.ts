interface DataItem {
    id: number;
    fileName: string;
    fileType: string;
    LocalDateTime: number;
}
  


const API_URL = 'http://localhost:8083/api/files/details';

export const fetchData = async (): Promise<DataItem[]> => {
  try {
    const response = await fetch(API_URL, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const data = await response.json();
    console.log('Fetched data:', data); // Log the data to see what is returned
    return data;
  } catch (error) {
    console.error('There was a problem with the fetch operation:', error);
    throw error;
  }
};
