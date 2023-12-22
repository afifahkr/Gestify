# Guide How to Build the Model Locally on Jupyter Notebook
Hello everyone, welcome to our machine learning repo, here is the guide how to build the model using hand_sign_detection.pynb locally in jupyter notebook.

## 1. Copy the projet directory from the repo
You can do it by `git clone https://github.com/afifahkr/Gestify.git` in wherever you want on your local using command prompt.
## 2. Set up the tensorflow object detection API environment in anaconda environment
You can set up by yourself or following this cool video tutorial by *Lazy Tech* on youtube. [click here]([https://www.example.com](https://www.youtube.com/watch?v=rRwflsS67ow)https://www.youtube.com/watch?v=rRwflsS67ow)
## 3. Download the full dataset 
Currently, in this repo only available 5 sign language vocab with 20 image each of them, download dataset [here](https://www.kaggle.com/datasets/zuhadharkasyalhikam/dataset-sistem-isyarat-bahasa-indonesia-sibi)<br>
After downloading, you can delete the existing directory named `train` and `test` and put the dataset folder in `\RealTimeObjectDetection\Tensorflow\workspace\images` 
## 4. Run the jupyter notebook 
Becareful and make sure you run the notebook in jupyter notebook trough the anaconda environment you just build in step 2.<br>
Then, you can run it one by one or do runtime.
## 5. Train the Model
After you run the 6th step inside jupyter note book (Train the Model), you can train the model by copying the output in 6th step on your anaconda prompt.<br>
It will take several hour depends on your Hardware spesification.<br>
In addition, you can customize the training configuration like batch size, and number of steps in 6th step script on the notebook. <br>
note: Do not forget to activate the tensorflow object detection API environment first.
## 6. Test the Model
After the training finished, you can test the model in realtime by continuing the notebook script on the 7th step.<br>
Then, you can type `q` to stop the testing
## 7. Export the model into h5 file
You can do this by running the script, you can customize where you want the .h5 will be generated and the .h5 file name also.
