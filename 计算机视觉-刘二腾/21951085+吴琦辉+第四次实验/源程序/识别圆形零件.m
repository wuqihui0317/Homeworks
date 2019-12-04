clear;
rgb=imread('hw4.jpg');
figure(1)
imshow(rgb);
for i=1:440
    for j=1:361
        if((rgb(i,j,1)>150&rgb(i,j,2)<100&rgb(i,j,3)<100)==0)
            rgb(i,j,1)=0;
            rgb(i,j,2)=0;
            rgb(i,j,3)=0;
        end
    end
end
figure(2)
imshow(rgb);