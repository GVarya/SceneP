using System.Collections;
using UnityEngine;
using VLB;

public class TestChange : MonoBehaviour
{
    public int channel;

    private Light lt;
   
    public void Start()
    {
        
        lt = GetComponent<Light>();
    }

  
    void OnGUI()
    {
        lt.intensity += Input.mouseScrollDelta.y * 0.1f;
    }

    public void getCommand(int channel, int value) {
        if (channel == this.channel)
            lt.intensity = value/100.0f;
    }

    public void FadeOut()
    {
        float updateTime = 0.01f;
        float lerpTime = 0.01f;//0.5f;
        if (lt != null)
        {
            float startIntensity = lt.intensity;
            StartCoroutine(_FadeOut(startIntensity, lerpTime, updateTime));
        }
    }

    private IEnumerator _FadeOut(float startIntensity, float lerpTime, float updateTime)
    {
        for (int i = 0; i < lerpTime / updateTime; i++)
        {
            lt.intensity = Mathf.Lerp(startIntensity, 0, i / (lerpTime / updateTime));
            yield return new WaitForSeconds(updateTime);
        }
    }

    public void Blink()
    {
        float updateTime = 0.01f;
        float lerpTime = 0.01f;//.20f;
        float maxIntensity = 5f;
        if (lt != null)
        {
            float startIntensity = lt.intensity;
            StartCoroutine(_Blink(startIntensity, maxIntensity, lerpTime, updateTime));
        }
    }

    private IEnumerator _Blink(float startIntensity, float maxIntensity, float lerpTime, float updateTime)
    {
        for (int i = 0; i < lerpTime / updateTime; i++)
        {
            lt.intensity = Mathf.Lerp(startIntensity, maxIntensity, i / (lerpTime / updateTime));
            yield return new WaitForSeconds(updateTime);
        }
        for (int i = 0; i < lerpTime / updateTime; i++)
        {
            lt.intensity = Mathf.Lerp(maxIntensity, startIntensity, i / (lerpTime / updateTime));
            yield return new WaitForSeconds(updateTime);
        }
    }

}
