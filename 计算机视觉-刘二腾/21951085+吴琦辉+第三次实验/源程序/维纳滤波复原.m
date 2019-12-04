clear; 

I=imread('pic.png');
I=rgb2gray(I);
figure; subplot(2,3,1);imshow(I);title('Ô­Í¼Ïñ'); 
[m,n]=size(I); 
F=fftshift(fft2(I)); 
k=0.0025; 
for u=1:m     %ÍË»¯Ä£ĞÍ
    for  v=1:n      
        H(u,v)=exp((-k)*(((u-m/2)^2+(v-n/2)^2)^(5/6)));   
    end
end

G0=F.*H; 
I0=real(ifft2(fftshift(G0))); 
noise=imnoise(zeros(size(I0)),'gaussian',0,0.001) ;
I1=I0+noise;
subplot(2,3,3);imshow(uint8(I1));title('Ä£ºıÍË»¯ÇÒÌí¼Ó¸ßË¹ÔëÉùµÄÍ¼Ïñ'); 

%ÄæÂË²¨
G=fftshift(fft2(I1)); %¸µÀïÒ¶±ä»»
F1=G./H; 
I2=ifft2(fftshift(F1)); 


%K=0.1Î¬ÄÉÂË²¨¸´Ô­
K=0.1;                   
for u=1:m     
    for  v=1:n      
        H(u,v)=exp(-k*(((u-m/2)^2+(v-n/2)^2)^(5/6)));     
        H0(u,v)=(abs(H(u,v)))^2;       
        H1(u,v)=H0(u,v)/(H(u,v)*(H0(u,v)+K));    
    end
end
F2=H1.*G; 
I3=ifft2(fftshift(F2));
subplot(2,3,4);imshow(uint8(I3));title('K=0.1ÄÉÂË²¨¸´Ô­Í¼');

%K=0.01Î¬ÄÉÂË²¨¸´Ô­
K=0.01;                   
for u=1:m     
    for  v=1:n      
        H(u,v)=exp(-k*(((u-m/2)^2+(v-n/2)^2)^(5/6)));     
        H0(u,v)=(abs(H(u,v)))^2;       
        H1(u,v)=H0(u,v)/(H(u,v)*(H0(u,v)+K));    
    end
end
F2=H1.*G; 
I3=ifft2(fftshift(F2));
subplot(2,3,5);imshow(uint8(I3));title('K=0.01ÄÉÂË²¨¸´Ô­Í¼');

%K=0.001Î¬ÄÉÂË²¨¸´Ô­
K=0.001;                   
for u=1:m     
    for  v=1:n      
        H(u,v)=exp(-k*(((u-m/2)^2+(v-n/2)^2)^(5/6)));     
        H0(u,v)=(abs(H(u,v)))^2;       
        H1(u,v)=H0(u,v)/(H(u,v)*(H0(u,v)+K));    
    end
end
F2=H1.*G; 
I3=ifft2(fftshift(F2));
subplot(2,3,6);imshow(uint8(I3));title('K=0.001ÄÉÂË²¨¸´Ô­Í¼');
