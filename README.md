# Centre-of-Attention-source

This is a 2-man project, with the purpose of applying Java GUI programming and Database programming. It has to solve a programming problem, which will be described below.

### The problem
#### Original text
"For this project, we're given an image in which some object of interest (e.g. a face, or a license plate, or an aircraft) appears as a large block of contiguous pixels all of the same colour. (Probably some image-processing has already occurred to achieve this, but we needn't worry about that.) We want to find the centre of the object in the image.

We'll do this by finding which pixels of the given colour have maximum depth. The depth of a pixel P is the minimum number of steps (up, down, left, or right) you have to take from P to reach either a pixel of a different colour or the edge of the image.

<p align="center">
pixel depths pic<br>
<img src="https://user-images.githubusercontent.com/24392632/86234745-9a4c5f00-bbc1-11ea-927b-f2e6ae686772.png">
</p>

In the picture, the red pixel marked ""3"" has a depth of 3: it takes at least 3 steps from there to reach something other than another red pixel. Note that the steps need not be all in the same direction. Only one red pixel has depth 3: the one right in the middle of the red region. Similarly, the blue pixel marked ""2"" has a depth of 2 (but it is not the only one with this depth). The green and purple pixels all have depth 1.

The pixels of a given colour with the largest depth will be found at the centre of the biggest solid region(s) of that colour. Those are the ones we want.

The function you'll write (central_pixels) belongs to the following data structure:

```cpp
struct Image
{
 unsigned *pixels;
 unsigned width, height;

 vector<unsigned> central_pixels(unsigned colour) const;
 // other functions ...
};
```

The image data consists of a one-dimensional array pixels of unsigned integers (or just integers, in languages that don't have unsigned integers as such), which correspond to pixels in row-by-row order. (That is, the top row of pixels comes first, from left to right, then the second row, and so on, with the pixel in the bottom right corner last of all.) The values of the pixels array elements represent colours via some one-to-one mapping whose details need not concern us.

The central_pixels function should find and return all the positions (pixels array indices) of the pixels having the greatest depth among all pixels of colour colour).

Note 1. The final test in the suite (Big_Test) is a 16-megapixel image (4 megapixels in the Python version), so you will need to consider the time and space requirements of your solution for images up to that size.

Note 2. The data in an Image object should not be assumed to be constant after instantiation. The test suite frequently modifies images and then re-tests them."

#### Problem summarry
Given a two dimensions matrix `A[][]` contains only integers. Define "`max_depth` of a pixel" as `d(x, y)`, is the shortest Manhattan distance from tile `[x][y]` to:
- Either, another tile `[u][v]` so that `A[x][y] ≠ A[u][v]`, (in other words, different colored pixels)
- Or either, just right outside the border of `A[][]`

Given an integer `k`. Find all tiles `[x][y]` so that `d(x,y)` is the greatest among all `A[x][y]` equals `k`.

### Demonstration
#### User Interface
Gồm 3 khu vực, bên trái là khu vực `Functions` chứa đa số các nút thực hiện chức năng chính, bên phải là khu vực `Input/Output` là nơi mà chương trình sẽ nhận và trả dữ liệu, ở giữa là khu vực `Canvas` là nơi mà chương trình sẽ vẽ đồ họa `Image` được mô phỏng tại ô `Input`, chương trình sẽ vẽ khi nút `Start` được bấm.

Consists of 3 area:
- The leftmost is called "Functions". Contains mostly buttons that will do what they are meant to do (based on the text on the button)
- The rightmost is called "Input/Output". This is where the program will read in the data, and output back results.
- The middle part is called "Canvas". This is where our program will produe an image, based on what the user has inputted (the `A[][]` matrix). Colors are randomly picked so that no different numbers has the same colors.

#### Images
##### When you just launch the program
![image](https://user-images.githubusercontent.com/24392632/85954634-22a0e900-b9a3-11ea-9e67-d9b745a03f0d.png)

##### A small test to demonstrate image rendering
![image](https://user-images.githubusercontent.com/24392632/85954969-a2c84e00-b9a5-11ea-9b0d-810b3be62f5d.png)

##### A slightly bigger test
![image](https://user-images.githubusercontent.com/24392632/85954644-35b3b900-b9a3-11ea-8a0c-44b433a93d40.png)

##### A medium-sized test (200x200)
![image](https://user-images.githubusercontent.com/24392632/85954663-58de6880-b9a3-11ea-830f-0686e488667a.png)
![image](https://user-images.githubusercontent.com/24392632/85954727-a8249900-b9a3-11ea-8e24-60b11bf68957.png)

Large test (around 1000x1000) makes Java's `BufferedImage` uses up huge memory and long allocate time so unfortunately there will be no images for larger testcases.
