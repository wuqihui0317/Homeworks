clear;
rgb=imread('hw4.jpg');
figure(1)
imshow(rgb);
for i=1:440
    for j=1:361
        if(((rgb(i,j,1)>200&rgb(i,j,2)>150&rgb(i,j,3)<50)||(rgb(i,j,1)>150&rgb(i,j,2)<100&rgb(i,j,3)<100))==0)
            rgb(i,j,1)=255;
            rgb(i,j,2)=255;
            rgb(i,j,3)=255;
        end
    end
end
figure(2)
imshow(rgb);


RGB = rgb
figure,
imshow(RGB),
title('Original Image');

% Step 2: 转化为灰度图像 
GRAY = rgb2gray(RGB);
figure,
imshow(GRAY),
title('Gray Image');

% Step 3: 图像二值化，以便为边界跟踪做准备

BW = im2bw(GRAY,0.8);
figure,
imshow(BW),
title('Binary Image');

% Step 4: 二值图像反色
BW = ~ BW;
figure,
imshow(BW);

[B,L] = bwboundaries(BW,'noholes');
title('Inverted Binary Image');

imshow(label2rgb(L,@jet,[.5 .5 .5]))
hold on
for k = 1:length(B)
  boundary = B{k};
  plot(boundary(:,2),boundary(:,1),'w','LineWidth',2)
end


stats = regionprops(L,'Area','Centroid');

threshold = 0.94;

for k = 1:length(B)
  boundary = B{k};
  delta_sq = diff(boundary).^2;    
  perimeter = sum(sqrt(sum(delta_sq,2)));
  area = stats(k).Area;
  metric = 4*pi*area/perimeter^2;
  metric_string = sprintf('%2.2f',metric);
  if metric > threshold
    centroid = stats(k).Centroid;
    plot(centroid(1),centroid(2),'ko');
  end
  text(boundary(1,2)-35,boundary(1,1)+13,metric_string,'Color','y',...
       'FontSize',14,'FontWeight','bold')
end
